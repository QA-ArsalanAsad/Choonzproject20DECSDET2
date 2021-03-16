import {getUsernameFromAuth} from '../utils.js';

export let clearAuth = async () => {
    let currentAuth = sessionStorage.getItem('auth');
    currentAuth = currentAuth.replaceAll('"', '');
    let success;
    sessionStorage.setItem('username', getUsernameFromAuth(currentAuth))
    await fetch(`user/logout/${currentAuth}`, {
        method: 'POST'
    }).then((response)=> {
        success = response.text();
    });
    // TODO fix logout bug
    console.log(success);
    // if (success == 'true') {
    //     console.log('Successfully Logged Out!');
    // } else {
    //     console.log('Unsuccessful Logout Try Again!')
    // }
    sessionStorage.removeItem('auth');
    sessionStorage.setItem('justLoggedOut', 'true');
    location.reload();
}
