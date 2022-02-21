const deleteButtons = document.getElementsByClassName("q-delete");

const addNewAlbumButton = document.getElementById("add-album");
const newAlbumModal = document.getElementById("new-album-modal");
const hideNewAlbumModalButton = document.getElementsByClassName("q-cancel-modal")[0];

for (let button of deleteButtons) {
    button.onclick = deleteAlbum
}

// addNewForm.onsubmit = submitNewAlbum
// submitNewButton.onclick = submitNewAlbum

addNewAlbumButton.onclick = showNewAlbumModal
hideNewAlbumModalButton.onclick = hideNewAlbumModal

function showNewAlbumModal() {
    newAlbumModal.style.display = "block";
}

function hideNewAlbumModal() {
    newAlbumModal.style.display = "none";
}

function submitNewAlbum() {
    console.log("Submitting new album");
    let form = new FormData(document.getElementById("new-album-form"));

    try {
        fetch('/api/albums', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "title": form.get("title"),
                "release": form.get("release"),
                "description": form.get("description"),
                "songIds": []
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
