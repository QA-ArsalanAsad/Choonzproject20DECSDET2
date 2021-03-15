import {populateMenu} from "./user-auth/account-management-dropdown.js";

let auth = sessionStorage.getItem('auth');

if (auth !== null) {
    console.log('Logged in!');
} else {
    console.log('Not Logged in!');
    populateMenu(false);
}