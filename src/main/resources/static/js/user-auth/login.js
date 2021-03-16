import {passwordToHash} from '../utils.js';

import '../bootstrap.bundle.js';

let login = () => {
    let username = document.querySelector('#username-input');
    let password = document.querySelector('#password-input');

    let usernameValue = username.value;
    let passwordValue = password.value;

    let successfulLogin;

    passwordToHash(passwordValue)
        .then((passwordHash) => {
            let bodyObj = {'userName': usernameValue, 'password': passwordHash};
            return bodyObj
        })
        .then((body) => {
            fetch('http://localhost:8082/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(body)
            }).then((response) => {
                if (response.status === 204) {
                    console.error("Unable to login (wrong credentials)");
                    updateFail("wrong-credentials");
                } else if (response.status === 200) {
                    let toastPopup = loginToast(usernameValue);
                    let body = document.querySelector('body');
                    body.append(toastPopup);
                    enableToast();
                    successfulLogin = true;
                    let auth = response.text();
                    return auth;
                } else {
                    console.error(`Failed with error ${response.status} code`);
                    updateFail(response.status)
                    successfulLogin = false;
                }
            }).then((auth) => {
                if (successfulLogin) {
                    sessionStorage.setItem('auth', JSON.stringify(auth));
                    window.location.replace('../index.html');
                }
            })
        })
        .finally(() => {
            username.value = '';
            password.value = '';

        })
}

let createFail = () => {
    const failed = document.createElement('p');
    failed.className = "failed-text my-3 mx-3";
    failed.id = "failed";

    const inputForm = document.querySelector('#input-form');
    inputForm.append(failed);
}

let updateFail = (status) => {
    const failed = document.querySelector('#failed');
    if (status === "wrong-credentials") {
        failed.innerHTML = "Sorry failed to login (wrong credentials)"
    } else {
        failed.innerHTML = `Sorry failed to login (error code ${status})`
    }
}

let loginToast = (name) => {

    let toastDiv = document.createElement('div');
    toastDiv.className = 'toast';
    toastDiv.id = 'login-toast';
    toastDiv.setAttribute('role', 'alert');
    toastDiv.setAttribute('aria-live', 'assertive');
    toastDiv.setAttribute('aria-atomic', 'true');

    let toastHeader = document.createElement('div');
    toastHeader.className = 'toast-header';
    toastHeader.innerHTML = '<strong>Login</strong>' +
        '    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>';

    let toastBody = document.createElement('div');
    toastBody.className = 'toast-body';
    toastBody.innerHTML = `Hi ${name}, you have successfully logged in`;

    toastDiv.append(toastHeader, toastBody);

    return toastDiv;
}

let enableToast =()=>{
    let toastElement = document.querySelector('.toast');
    let toast = new Toast(toastElement);
    toast.show();
}

let submitButton = document.querySelector('#submit-detail-button');
submitButton.addEventListener('click', login);

createFail();