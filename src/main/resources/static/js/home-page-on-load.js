import {populateMenu} from "./user-auth/account-management-dropdown.js";
import {enableToast} from './user-auth/toasts.js';
import {getUsernameFromAuth} from './utils.js';
import {artistPageBuild} from './page-builder/artist/artist-page-builder.js';
import {genrePageBuild} from './page-builder.genre/genre-page-builder.js';
import {playlistPageBuild} from './page-builder.playlist/playlist-page-builder.js';
import {albumPageBuild} from './page-builder.album/album-page-builder.js';
import {trackPageBuild} from './page-builder.track/track-page-builder.js';

let auth = sessionStorage.getItem('auth');

let makePlaylistLabel = async ()=>{
    let username;
    await getUsernameFromAuth(auth)
        .then((response)=>{
            username = response;
        })
    let playlistLabel = document.querySelector('#welcome-user');
    playlistLabel.innerHTML = `Hi there ${username} &#128075, these are your playlists:`;
}

if (auth !== null) {
    auth = auth.replaceAll('"', '');
    console.log('Logged in!');
    populateMenu(true);
    makePlaylistLabel();
    let justLoggedIn = sessionStorage.getItem('justLoggedIn');
    if (justLoggedIn === 'true') {
        enableToast('login');
        sessionStorage.removeItem('justLoggedIn');
    }
} else {
    console.log('Not Logged in!');
    populateMenu(false);
    let justLoggedOut = sessionStorage.getItem('justLoggedOut');
    if (justLoggedOut === 'true') {
        enableToast('logout');
        sessionStorage.removeItem('justLoggedOut');
    }
}

let pageBuilder =(type)=>{
    if (type === 'Artist') {
        artistPageBuild();
    } else if (type === 'Genre') {
        genrePageBuild();
    } else if (type === 'Playlist') {
        playlistPageBuild();
    } else if (type === 'Album') {
        albumPageBuild();
    } else if (type === 'Track') {
        trackPageBuild();
    }
}
let searchButton = document.querySelector('#search-button');
searchButton.addEventListener('click', ()=>{pageBuilder(document.querySelector('#select-bar').value)});
