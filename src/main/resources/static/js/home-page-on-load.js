import {populateMenu} from "./user-auth/account-management-dropdown.js";
import {enableToast} from './user-auth/toasts.js';

let auth = sessionStorage.getItem('auth');

if (auth !== null) {
    console.log('Logged in!');
    populateMenu(true);
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