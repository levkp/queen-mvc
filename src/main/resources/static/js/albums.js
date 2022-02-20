const deleteButtons = document.getElementsByClassName("q-delete")

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
