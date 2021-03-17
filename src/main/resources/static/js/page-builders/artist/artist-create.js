export let artistCreate = (isEmpty) => {
    if (document.contains(document.querySelector('#artist-create-button'))) {
        document.querySelector('#centre-col').removeChild(document.querySelector('#artist-create-button'));
    }
    let createButton = document.createElement('button');
    createButton.className = 'button-as-link regular-text';
    createButton.id = 'artist-create-button';
    if (isEmpty === 'empty') {
        createButton.innerHTML = "Looks like it's empty here, click to add something!"
    } else if (isEmpty === 'not empty') {
        createButton.innerText = "Not seeing what you want? (click to add your own)";
    }
    createButton.addEventListener('click', () => {
        let artistModalElement = document.querySelector('#artist-create-modal');
        let artistModal = new bootstrap.Modal(artistModalElement);
        window.artistModal = artistModal;
        let submitButton = document.querySelector('#artist-modal-submit');
        submitButton.addEventListener('click', submitButtonSendAndHide);
        artistModal.show();
    })
    return createButton;
}

let artistSubmit = () => {
    let artistName = document.querySelector('#artist-modal-name').value;
    let bodyObj = {'name': artistName};
    fetch('/artists/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).then((response) => {
        if (response.status === 201) {
            console.log('Artist Successfully Created');
        } else {
            console.log('Error in creation')
        }
    }).finally(() => {
        document.querySelector('#artist-modal-name').value = '';
    })
}

let submitButtonSendAndHide =()=>{
    artistSubmit();
    window.artistModal.hide();
}