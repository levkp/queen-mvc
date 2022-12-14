import Modal from "modal-vanilla/lib/modal";

const csrfHeader = document.querySelector("meta[name='_csrf_header']").content;
const csrfToken = document.querySelector("meta[name='_csrf']").content;

function showErrorModal(title, message) {
    new Modal({
        title: title,
        message: message,
    }).show();
}

// Delete
const deleteButtons = document.getElementsByClassName("q-delete");

for (let button of deleteButtons) {
    button.onclick = deleteAlbum;
}

async function deleteAlbum(event) {
    let id = event.target.value;

    const headers = {};
    headers[csrfHeader] = csrfToken;

    await fetch(`/api/albums/${id}`, {
        method: "DELETE",
        headers
    }).then(response => {
        if (response.status === 204) {
            document.getElementById(`album-${id}`).remove();
        } else {
            console.error(response);
            showErrorModal("Album wasn't deleted", response.body);
        }
    })
}


// Create
const newAlbumForm = document.getElementById("new-album-form");
const newAlbumButton = document.getElementById("add-album");
const albumsTable = document.getElementById("albums-table");

let newAlbumModal = new Modal({
   el: document.getElementById("new-album-modal")
});

newAlbumButton.onclick = function() {
    newAlbumModal.show();
}

newAlbumForm.onsubmit = function(event) {
    event.preventDefault();
    newAlbumModal.hide();

    const fd = new FormData(newAlbumForm);
    const headers = {};
    headers[csrfHeader] = csrfToken;
    headers["Content-Type"] = "application/json";

    fetch('/api/albums', {
        method: "POST",
        headers,
        body: JSON.stringify({
            "title": fd.get("title"),
            "release": fd.get("release"),
            "description": fd.get("description").toString(),
            "songIds": fd.getAll("song-ids")
        })
    }).then(response => {
        if (response.status === 201) {
            response.json().then(data => {
                let tableRows = albumsTable.rows;
                let newRow = tableRows[tableRows.length - 1].cloneNode(true);
                let actionButtons = newRow.getElementsByTagName("button");

                newRow.id = `album-${data.id}`;
                newRow.children[0].innerText = data.id;
                newRow.children[1].innerText = data.title;
                newRow.children[2].innerText = data.release;
                newRow.children[3].innerText = data.songIds.length;

                actionButtons[0].value = data.id;
                actionButtons[0].onclick = readAlbum
                actionButtons[1].value = data.id;
                actionButtons[1].onclick = deleteAlbum;

                albumsTable.append(newRow);
            })
        } else {
            console.error(response);
            showErrorModal("Album wasn't created", response.body);
        }
    })

    return false; // To prevent onsubmit triggering a page refresh
}

// Update
const editTitleButton = document.getElementById("edit-title");
const editReleaseButton = document.getElementById("edit-release");
const editTracklistButton = document.getElementById("edit-tracklist");
const editDescButton = document.getElementById("edit-desc");

editTitleButton.onclick = function (event) {
    editTitleButton.parentElement.innerHTML =
        `<input type="text" id="edited-title" class="form-control" name="title" minlength="4" maxlength="30" required>
         <i class="bi bi-check-lg"></i>`
}

editReleaseButton.onclick = function() {
    const releaseTd = document.getElementById("album-release");
    let date = releaseTd.innerText;

    releaseTd.innerHTML =
        `<input type="date" id="edited-release" class="form-control" name="release" required>
         <i class="bi bi-check-lg"></i>`;

}

function showSubmitEditButton() {
    document.getElementById("submit-edit").style.display = "inline:";
}


// Read
const readButtons = document.getElementsByClassName("q-read");
const readAlbumModal = document.getElementById("read-album-modal")

for (let button of readButtons) {
    button.onclick = readAlbum;
}

function readAlbum(event) {
    let id = event.target.value;

    console.log("Showing details for album " + id);

    try {
        fetch(`/api/albums/${id}`, {
            method: "GET",
            headers: { "Accept": "application/json" }
        }).then(response => {
            if (response.status === 200) {
                response.json().then(async data => {

                    document.getElementById("album-title").innerText = data.title;
                    document.getElementById("album-release").innerText = data.release;
                    document.getElementById("album-length").innerText = data.length;
                    document.getElementById("album-desc").innerText = data.description;

                    let songs = [];

                    for (let id of data.songIds) {
                        (await fetchSong(id)).json().then(song => {
                            songs.push(song);
                        });
                    }

                    let trackList = document.getElementById("album-songs");
                    let genres = "";

                    songs.forEach(s => {
                        console.log(s);
                        genres.concat(s['genresToString']);
                        let li = document.createElement("li");
                        li.className = "list-group-item";
                        li.innerText = s.title;
                        trackList.appendChild(li);
                    });

                    console.log("Genres:");
                    console.log(genres);
                });

            } else {
                console.log(response.status);
            }
        })
    } catch (exc) {
        console.error(exc);
    }

    new Modal({
        el: readAlbumModal
    }).show();
}

async function fetchSong(id) {
    console.log("Sending request for song " + id);

    return fetch(`/api/songs/${id}`, {
        method: "GET",
        headers: { "Accept": "application/json" }
    });
}
