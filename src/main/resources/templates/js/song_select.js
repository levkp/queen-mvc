window.onload = function () {
    let showSongListButton = document.getElementById("song-list-btn");
    let songList = document.getElementById("song-list");
    let listHidden = true;

    showSongListButton.onclick = function () {
        songList.style.display = listHidden ? "flex" : "none";
        listHidden = !listHidden;
    };
}
