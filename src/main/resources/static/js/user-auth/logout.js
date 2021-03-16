export let clearAuth = async () => {
    let currentAuth = sessionStorage.getItem('auth');
    let success;
    await fetch(`user/logout/${currentAuth}`, {
        method: 'POST'
    }).then((response)=> {
        success = response.text();
    });
    if (success === "true") {
        console.log('Successfully Logged Out!');
    } else {
        console.log('Unsuccessful Logout Try Again!')
    }
    sessionStorage.removeItem('auth');
    location.reload();
}
