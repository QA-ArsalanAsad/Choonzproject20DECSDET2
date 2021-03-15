import {passwordToHash} from './utils.js';

let login = () => {
    let username = document.querySelector('#username-input');
    let password = document.querySelector('#password-input');

    let usernameValue = username.value;
    let passwordValue = password.value;

    passwordToHash(passwordValue)
        .then((passwordHash) => {
            let bodyObj = {'username': usernameValue, 'password': passwordHash};
            return bodyObj
        })
        .then((body) => {
            fetch('http://localhost:8082/users/login', {
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
                    let auth = response.text();
                    return auth;
                } else {
                    console.error(`Failed with error ${response.status} code`);
                    updateFail(response.status)
                }
            }).then((auth)=>{
                sessionStorage.setItem('auth', JSON.stringify(auth));
                window.location.replace('../index.html');
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

let submitButton = document.querySelector('#submit-detail-button');
submitButton.addEventListener('click', login);

createFail();