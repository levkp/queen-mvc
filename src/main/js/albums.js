// let currentModal;
//
// const modalCloseButtons = document.getElementsByClassName("q-modal-close");
//
// for (let button of modalCloseButtons) {
//
// }



// Delete
const deleteButtons = document.getElementsByClassName("q-delete");

for (let button of deleteButtons) {
    button.onclick = deleteAlbum
}

async function deleteAlbum(event) {
    let id = event.target.value;

    try {
        fetch(`/api/albums/${id}`, {
            method: "DELETE",
        }).then(
            response => {
                if (response.status === 200) {
                    document.getElementById(`album-${id}`).remove();
                } else {
                    // Todo
                    console.error(response);
                }
            }
        );
    } catch (e) {
        console.error(e);
    }
}

// Create
const newAlbumForm = document.getElementById("new-album-form");
const addAlbumButton = document.getElementById("add-album");
const albumCreatorModal = document.getElementById("new-album-modal");
const cancelNewAlbumModal = document.getElementById("cancel-new-btn");
const albumsTable = document.getElementById("albums-table");

cancelNewAlbumModal.onclick = hideNewAlbumModal
addAlbumButton.onclick = showNewAlbumModal
newAlbumForm.onsubmit = submitNewAlbum

function showNewAlbumModal() {
    albumCreatorModal.style.display = "block";
}

function hideNewAlbumModal() {
    albumCreatorModal.style.display = "none";
}

// Todo: urgent: NetworkError???
 function submitNewAlbum() {
    const fd = new FormData(document.getElementById("new-album-form"));

    fetch('/api/albums', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "title": fd.get("title"),
            "release": fd.get("release"),
            "description": fd.get("description"),
            "songIds": fd.getAll("song-ids")
        })
    }).then(response => {
         if (response.status === 200) {
             response.json().then(data => {
                 let rows = albumsTable.rows;
                 let newRow = rows[rows.length - 1].cloneNode(true);
                 let actionButtons = newRow.getElementsByTagName("button");

                 newRow.id = `album-${data.id}`;
                 newRow.children[0].innerText = data.id;
                 newRow.children[1].innerText = data.title;
                 newRow.children[2].innerText = data.release;
                 newRow.children[3].innerText = data.songIds.length;

                 actionButtons[0].value = data.id;
                 actionButtons[0].onclick = showReadAlbumModal;
                 actionButtons[1].value = data.id;
                 actionButtons[1].onclick = showNewAlbumModal;
                 actionButtons[2].value = data.id;
                 actionButtons[2].onclick = deleteAlbum;

                 albumsTable.append(newRow);

                 console.log(newRow);
             });

         } else {
             console.log(response);
         }
     })


     hideNewAlbumModal();
    return false; // To prevent onsubmit triggering a page refresh
}

// Update
const editButtons = document.getElementsByClassName("q-edit");


const editAlbumModal = document.getElementById("edit-album-modal");

for (let button of editButtons) {
    button.onclick = editAlbum;
}

function showEditAlbumModal() {
    editAlbumModal.style.display = "block";
}

function hideEditAlbumModal() {
    editAlbumModal.style.display = "hide";
}

function editAlbum(event) {
    showEditAlbumModal();

}

// Read
const readButtons = document.getElementsByClassName("q-read");
const readAlbumModal = document.getElementById("read-album-modal")

for (let button of readButtons) {
    button.onclick = readAlbum;
}

function showReadAlbumModal() {
    readAlbumModal.style.display = "block";
}

function hideReadAlbumModal() {
    readAlbumModal.style.display = "none";
}

function readAlbum(event) {
    let id = event.target.value;

    console.log("Showing details for album " + id);

    try {
        fetch(`/api/albums/${id}`, {
            method: "GET",
            headers: {
                "Accept": "application/json"
            }
        }).then(response => {
            if (response.status === 200) {
                response.json().then(data => {
                    console.log(data);

                    document.getElementById("album-title").innerText = data.title;
                    document.getElementById("album-release").innerText = data.release;
                    document.getElementById("album-length").innerText = data.length;
                    document.getElementById("album-desc").innerText = data.description;


                });
            } else {
                console.log(response.status);
            }
        })
    } catch (exc) {
        console.error(exc);
    }

    showReadAlbumModal();
}
