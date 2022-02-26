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
const addNewAlbumButton = document.getElementById("add-album");
const newAlbumModal = document.getElementById("new-album-modal");
const cancelNewAlbumModal = document.getElementsByClassName("q-cancel-modal")[0];

addNewAlbumButton.onclick = showNewAlbumModal
cancelNewAlbumModal.onclick = hideNewAlbumModal

function showNewAlbumModal() {
    newAlbumModal.style.display = "block";
}

function hideNewAlbumModal() {
    newAlbumModal.style.display = "none";
}

// Todo: urgent: NetworkError???
function submitNewAlbum() {
    console.log("Submitting new album");

    const fd = new FormData(document.getElementById("new-album-form"));

    console.log("Keys: ");
    for(let key of fd.keys()) {
        console.log(key);
    }

    console.log("Values: ");
    for(let key of fd.values()) {
        console.log(key)
    }

    try {
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
        }).then(
            response => {
                console.log(response);
            }
        )
    } catch (e) {
        console.error(e);
    }

    hideNewAlbumModal();
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
            console.log(response);

            if (response.status === 200) {
                console.log(response.body)
            }
        })
    } catch (exc) {
        console.error(exc);
    }


    showReadAlbumModal();
}
