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

    // await axios.delete(`/api/albums/${id}`)
    //     .then((response) => {
    //         console.log(response);
    //         document.getElementById(`album-${id}`).remove();
    //     })
    //     .catch((error) => {
    //         console.error(error);
    //         showErrorModal("Error while deleting album", error.toString())
    //     });

    await fetch(`/api/albums/${id}`, {
        method: "DELETE",
        headers: {
            "X-XSRF-TOKEN": csrfToken,
        }
    }).then(response => {
        if (response.status === 200) {
            document.getElementById(`album-${id}`).remove();
        } else {
            console.error(response);
            showErrorModal("Error while deleting album", response.toString());
        }
    })
}



// Create
//
// let newAlbumModal = new Modal({
//     el: document.getElementById("new-album-modal")
// }).show();

const newAlbumForm = document.getElementById("create-album-form");
const addAlbumButton = document.getElementById("add-album");

let createAlbumModal = new Modal({
   el: document.getElementById("create-album-modal")
});

addAlbumButton.onclick = function() {
    createAlbumModal.show();
}

newAlbumForm.onsubmit = function() {
    createAlbumModal.hide();
    const fd = new FormData(newAlbumForm);


}



// cancelNewAlbumModal.onclick = hideNewAlbumModal
// addAlbumButton.onclick = showNewAlbumModal
// newAlbumForm.onsubmit = submitNewAlbum
//
// function showNewAlbumModal() {
//     albumCreatorModal.style.display = "block";
// }
//
// function hideNewAlbumModal() {
//     albumCreatorModal.style.display = "none";
// }
//
// // Todo: urgent: NetworkError???
//  function submitNewAlbum() {
//     const fd = new FormData(document.getElementById("new-album-form"));
//
//     fetch('/api/albums', {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json"
//         },
//         body: JSON.stringify({
//             "title": fd.get("title"),
//             "release": fd.get("release"),
//             "description": fd.get("description").toString(),
//             "songIds": fd.getAll("song-ids")
//         })
//     }).then(response => {
//          if (response.status === 200) {
//              response.json().then(data => {
//                  let rows = albumsTable.rows;
//                  let newRow = rows[rows.length - 1].cloneNode(true);
//                  let actionButtons = newRow.getElementsByTagName("button");
//
//                  newRow.id = `album-${data.id}`;
//                  newRow.children[0].innerText = data.id;
//                  newRow.children[1].innerText = data.title;
//                  newRow.children[2].innerText = data.release;
//                  newRow.children[3].innerText = data.songIds.length;
//
//                  actionButtons[0].value = data.id;
//                  actionButtons[0].onclick = showReadAlbumModal;
//                  actionButtons[1].value = data.id;
//                  actionButtons[1].onclick = showNewAlbumModal;
//                  actionButtons[2].value = data.id;
//                  actionButtons[2].onclick = deleteAlbum;
//
//                  albumsTable.append(newRow);
//
//                  console.log(newRow);
//              });
//
//          } else {
//              console.log(response);
//          }
//      })
//
//
//      hideNewAlbumModal();
//     return false; // To prevent onsubmit triggering a page refresh
// }

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
                       console.log(data);

                    document.getElementById("album-title").innerText = data.title;
                    document.getElementById("album-release").innerText = data.release;
                    document.getElementById("album-length").innerText = data.length;
                    document.getElementById("album-desc").innerText = data.description;

                    console.log("Fetching songs for album");

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
