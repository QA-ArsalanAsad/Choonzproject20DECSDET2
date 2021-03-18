export let playlistCreate = (isEmpty) => {
    if (document.contains(document.querySelector('#playlist-create-button'))) {
        document.querySelector('#centre-col').removeChild(document.querySelector('#playlist-create-button'));
    }
    let createButton = document.createElement('button');
    createButton.className = 'button-as-link regular-text';
    createButton.id = 'playlist-create-button';
    if (isEmpty === 'empty') {
        createButton.innerHTML = "Looks like it's empty here, click to add something!"
    } else if (isEmpty === 'not empty') {
        createButton.innerText = "Not seeing what you want? (click to add your own)";
    }
    createButton.addEventListener('click', () => {
        let playlistModalElement = document.querySelector('#playlist-create-modal');
        let playlistModal = new bootstrap.Modal(playlistModalElement);
        window.playlistModal = playlistModal;
        let submitButton = document.querySelector('#playlist-modal-submit');
        submitButton.addEventListener('click', submitButtonSendAndHide);
        playlistModal.show();
    })
    return createButton;
}

let playlistSubmit = async ()=>{
    let playlistName = document.querySelector('#playlist-modal-name');
    let playlistDesc = document.querySelector('#playlist-modal-desc');
    let bodyObj = {'name': playlistName, 'description': playlistDesc, 'artwork': 'NOT IMPLEMENTED YET'};
    let authToken = sessionStorage.getItem('auth');
    let userID;
    await fetch(`/user/read/auth${authToken}`)
        .then((response)=>{
            return response.json();
        }).then((responseData)=>{
            userID = responseData['id'];
        })

    fetch(`/playlists/create/${userID}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).finally(()=>{
        document.querySelector('#playlist-modal-name').value = '';
        document.querySelector('#playlist-modal-desc').value = '';
    })
}

let submitButtonSendAndHide =()=>{
    playlistSubmit();
    window.genreModal.hide();
}