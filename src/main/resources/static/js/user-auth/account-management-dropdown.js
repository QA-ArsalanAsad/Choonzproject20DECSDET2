import {clearAuth} from "./logout.js";

let accountManagementDropdown = document.querySelector('#account-management');

export let populateMenu = async (loggedIn)=>{
    if (loggedIn) {
        let logout = document.createElement('button');
        logout.className = 'dropdown-item';
        logout.innerText = 'Logout';
        logout.addEventListener('click', async ()=>{clearAuth()})

        let item1 = document.createElement('li');
        item1.append(logout);
        accountManagementDropdown.append(item1);
    } else {
        let login = document.createElement('a');
        login.className = 'dropdown-item';
        login.href = '/account-management/login.html';
        login.innerText = 'Login';

        let register = document.createElement('a');
        register.className = 'dropdown-item';
        register.href = '/account-management/registration.html';
        register.innerText = 'Register';

        let item1 = document.createElement('li');
        item1.append(login);
        let item2 = document.createElement('li');
        item2.append(register);

        accountManagementDropdown.append(item1, item2);
    }
}