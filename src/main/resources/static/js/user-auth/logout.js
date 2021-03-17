import {getUsernameFromAuth} from '../utils.js';

export let clearAuth = async () => {
    let currentAuth = sessionStorage.getItem('auth');
    currentAuth = currentAuth.replaceAll('"', '');
    let success;
    let username;
    await getUsernameFromAuth(currentAuth)
        .then((response)=>{
            username = response;
        })
    sessionStorage.setItem('username', username);
    await fetch(`user/logout/${currentAuth}`, {
        method: 'POST'
    }).then((response)=> {
        return response.text();
    }).then((responseData)=>{
        success = responseData;
    })
    if (success === 'false') {
        console.log('Successfully Logged Out!');
    } else {
        console.log('Unsuccessful Logout Try Again!');
    }
    sessionStorage.removeItem('auth');
    sessionStorage.setItem('justLoggedOut', 'true');
    location.reload();
}
