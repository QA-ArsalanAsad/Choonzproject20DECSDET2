export let albumCreate = (isEmpty) => {
    if (document.contains(document.querySelector('#album-create-button'))) {
        document.querySelector('#centre-col').removeChild(document.querySelector('#album-create-button'));
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
        let albumModalElement = document.querySelector('#album-create-modal');
        let albumModal = new bootstrap.Modal(albumModalElement);
        window.albumModal = albumModal;
        let submitButton = document.querySelector('#album-modal-submit');
        submitButton.addEventListener('click', submitButtonSendAndHide);
        albumModal.show();
    })
    return createButton;
}

let albumSubmit = async () => {
    let albumName = document.querySelector('#album-modal-name').value;
    let artistName = document.querySelector('#album-artist-modal-name').value;
    let genreName = document.querySelector('#album-genre-modal-name').value;

    let artistID;
    let genreID;

    await fetch('/artists/read')
        .then((response)=>{
            return response.json();
        }).then((responseData)=>{
            for (let artist in responseData) {
                if (responseData[artist]['name'].toLowerCase()  === artistName.toLowerCase()) {
                    artistID = responseData[artist]['id'];
                }
            }
        })

    await fetch('/genres/read')
        .then((response)=>{
            return response.json();
        }).then((responseData)=>{
            for (let genre in responseData) {
                if (responseData[genre]['name'].toLowerCase()  === genreName.toLowerCase()) {
                    genreID = responseData[genre]['id'];
                }
            }
        })

    let bodyObj = {'name': albumName, 'cover': 'Not Implemented Yet'};
    fetch(`/albums/create/${artistID}/${genreID}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).then((response) => {
        if (response.status === 201) {
            console.log('Album Successfully Created');
        } else {
            console.log('Error In Creation')
        }
    }).finally(() => {
        document.querySelector('#album-modal-name').value = '';
        document.querySelector('#album-artist-modal-name').value = '';
        document.querySelector('#album-genre-modal-name').value = '';
    })
}

let submitButtonSendAndHide =()=>{
    albumSubmit();
    window.albumModal.hide();
}