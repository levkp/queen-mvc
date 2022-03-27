import Modal from "modal-vanilla/lib/modal";

const csrfHeader = document.querySelector("meta[name='_csrf_header']").content;
const csrfToken = document.querySelector("meta[name='_csrf']").content;

const deleteAllBtn = document.getElementById("delete-all");

function showErrorModal(message) {
    new Modal({
        title: "Unable to complete action",
        message: message,
    }).show();
}

deleteAllBtn.onclick = function () {
    Modal.confirm("Are you sure?")
        .show()
        .once("dismiss", function (modal, event, button) {
            const headers = {};
            headers[csrfHeader] = csrfToken;

            if (button && button.value) {
                fetch("/api/albums/", {
                    method: "DELETE",
                    headers
                }).then(response => {
                    if (response.status !== 200) {
                        console.error(response);
                        showErrorModal(response.toString());
                    }
                })
            }
    })
}

