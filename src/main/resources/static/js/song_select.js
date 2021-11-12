window.onload = function () {
    let showSongListButton = document.getElementById("song-list-btn");
    let songList = document.getElementById("song-list");
    let listHidden = true;

    showSongListButton.onclick = function () {
        showSongListButton.textContent = showSongListButton.textContent.replace(
            listHidden ? "see" : "hide",
            listHidden ? "hide" : "see"
        );
        songList.style.display = listHidden ? "inherit" : "none";
        listHidden = !listHidden;
    };
}
