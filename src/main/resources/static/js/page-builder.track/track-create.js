export let trackCreate = (isEmpty) => {
    if (document.contains(document.querySelector('#track-create-button'))) {
        document.querySelector('#centre-col').removeChild(document.querySelector('#track-create-button'));
    }
    let createButton = document.createElement('button');
    createButton.className = 'button-as-link regular-text';
    createButton.id = 'track-create-button';
    if (isEmpty === 'empty') {
        createButton.innerHTML = "Looks like it's empty here, click to add something!";
    } else if (isEmpty === 'not empty') {
        createButton.innerText = "Not seeing what you want? (click to add your own)";
    }

    createButton.addEventListener('click', () => {
        let trackModalElement = document.querySelector('#track-create-modal');
        let trackModal = new bootstrap.Modal(trackModalElement);
        window.trackModal = trackModal;
        let submitButton = document.querySelector('#track-modal-submit');
        submitButton.addEventListener('click', submitButtonSendAndHide);
        trackModal.show();
    })
    return createButton;
}

let trackSubmit = async () => {
    let trackName = document.querySelector('#track-modal-name').value;
    let duration = document.querySelector('#track-modal-duration').value;
    let albumName = document.querySelector('#track-artist-modal-name').value;
    duration = Number.parseInt(duration);

    let albumID;

    await fetch('/albums/read')
        .then((response) => {
            return response.json();
        }).then((responseData) => {
            for (let album in responseData) {
                if (responseData[album]['name'].toLowerCase() === albumName.toLowerCase()) {
                    albumID = responseData[album]['id'];
                }
            }
        })

    let bodyObj = {'name': trackName, 'duration': duration, 'lyrics': 'Not Implemented Yet'};
    fetch(`/tracks/create/${albumID}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).then((response) => {
        if (response.status === 201) {
            console.log('Track Successfully Created');
        } else {
            console.log('Error In Creation')
        }
    }).finally(()=>{
        document.querySelector('#track-modal-name').value = '';
        document.querySelector('#track-modal-duration').value = '';
        document.querySelector('#track-artist-modal-name').value = '';
    })
}

let submitButtonSendAndHide = () => {
    trackSubmit();
    window.trackModal.hide();
}