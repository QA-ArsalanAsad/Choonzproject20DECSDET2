import {populateMenu} from "./user-auth/account-management-dropdown.js";
import {enableToast} from './user-auth/toasts.js';
import {getUsernameFromAuth} from './utils.js';
import {artistPageBuild} from './page-builders/artist/artist-page-builder.js';

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
    }
}
let searchButton = document.querySelector('#search-button');
searchButton.addEventListener('click', ()=>{pageBuilder(document.querySelector('#select-bar').value)});
