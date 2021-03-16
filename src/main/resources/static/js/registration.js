import {passwordToHash} from './utils.js';

let register =()=>{
    let username = document.querySelector('#username-input');
    let password = document.querySelector('#password-input');
    let passwordRe = document.querySelector('#re-enter-password-input');

    let usernameValue = username.value;
    let passwordValue = password.value;
    let passwordReValue = passwordRe.value;

    if (passwordValue !== passwordReValue) {
        console.error('Passwords do not match try again');
    }

    else {
        passwordToHash(passwordValue)
            .then((passwordHash)=>{
                const bodyObj = {'username': usernameValue, 'password': passwordHash};
                return bodyObj;
            })
            .then((body)=>{
                fetch('http://localhost:8082/users/signup', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(body)
                }).then((response)=>{
                    if (response.status === 400) {
                        console.error("Failed to create user, already exists");
                        updateFail("exists");
                    } else if (response.status === 201) {
                        window.location.replace('login.html');
                    } else {
                        console.error(`Failed with error ${response.status} code`);
                        updateFail(response.status)
                    }
                })
            })
            .finally(()=>{
                username.value = '';
                password.value = '';
                passwordRe.value = '';
            })
    }
}

let createFail =()=>{
    const failed = document.createElement('p');
    failed.className = "failed-text my-3 mx-3";
    failed.id = "failed";

    const inputForm = document.querySelector('#input-form');
    inputForm.append(failed);
}

let updateFail =(status)=>{
    const failed = document.querySelector('#failed');
    if (status==="exists") {
        failed.innerHTML = "Sorry failed to create user (already exists)"
    } else {
        failed.innerHTML = `Sorry failed to create user (error code ${status})`
    }
}

let submitButton = document.querySelector('#submit-detail-button');
submitButton.addEventListener('click', register);

createFail();