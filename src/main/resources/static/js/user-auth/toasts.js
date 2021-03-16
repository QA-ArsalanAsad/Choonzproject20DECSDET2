import {getUsernameFromAuth} from '../utils.js';

export let enableToast = async (state) =>{
    if (state === 'login') {
        let authToken = sessionStorage.getItem('auth');
        authToken = authToken.replaceAll('"', '');
        let username;
        await getUsernameFromAuth(authToken)
            .then((response)=>{

            });

        let toastBody = document.querySelector('.toast-body');
        toastBody.innerHTML = `Welcome back ${username}, you have successfully logged in`;
        let toastElement = document.querySelector('#login-toast');
        let toast = new bootstrap.Toast(toastElement);
        toast.show();
    } else if (state === 'logout') {
        let username = sessionStorage.getItem('username');
        sessionStorage.removeItem('username');
        let toastHeaderTest = document.querySelector('#toast-header-text');
        toastHeaderTest.innerHTML = 'Choonz Logout';
        let toastBody = document.querySelector('.toast-body');
        toastBody.innerHTML = `Goodbye ${username}, you have successfully logged out`;
        let toastElement = document.querySelector('#login-toast');
        let toast = new bootstrap.Toast(toastElement);
        toast.show();
    }
}