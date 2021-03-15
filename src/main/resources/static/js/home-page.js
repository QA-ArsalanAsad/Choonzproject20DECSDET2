import {getTop10, getArtists, getGenres, getPlaylists, getAlbums, getTracks} from './utils.js'
import {additionLink} from "./utils-create.js";

let auth = sessionStorage.getItem('auth');

if (auth == null) {
    console.log("No Auth");
    let centreCol = document.querySelector('#centre-col');

    let header = document.createElement('div');
    header.innerHTML = "<span class=\"regular-text\" style=\"float: left\">Trending Artists</span>" +
        "               <span class=\"regular-text\" style=\"float: right\">Gathered From LastFM</span>" +
        "               <br>"
    centreCol.append(header);

    let trending = document.createElement('div');

    let topTenTable = getTop10();
    trending.append(topTenTable);

    centreCol.append(trending);
} else {
    console.log("Logged In");
    let centreCol = document.querySelector('#centre-col');
    centreCol.innerHTML = "";
}

let search =()=>{
    let centreCol = document.querySelector('#centre-col');
    centreCol.innerHTML = "";
    let category = document.querySelector('#select-bar').value;
    let name = document.querySelector('#input-bar').value;
    if (category === "Artist") {
        let table = getArtists(name);
        table.id = "results-table";
        table.className = "full-size-table";
        centreCol.append(table);
        centreCol.append(additionLink("Artist"));
    } else if (category === "Genre") {
        let table = getGenres(name);
        table.id = "results-table";
        table.className = "full-size-table";
        centreCol.append(table);
        centreCol.append(additionLink("Genre"));
    } else if (category === "Playlist") {
        let table = getPlaylists(name);
        table.id = "results-table";
        table.className = "full-size-table";
        centreCol.append(table);
        centreCol.append(additionLink("Playlist"));
    } else if (category === "Album") {
        let table = getAlbums(name);
        table.id = "results-table";
        table.className = "full-size-table";
        centreCol.append(table);
        centreCol.append(additionLink("Album"));
    } else if (category === "Track") {
        let table = getTracks(name);
        table.id = "results-table";
        table.className = "full-size-table";
        centreCol.append(table);
        centreCol.append(additionLink("Track"));
    }
}

let searchButton = document.querySelector('#search-button');
searchButton.addEventListener('click', search);