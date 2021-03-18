export let genreCreate =(isEmpty) => {
    if (document.contains(document.querySelector('#genre-create-button'))) {
        document.querySelector('#centre-col').removeChild(document.querySelector('#artist-create-button'));
    }
    let createButton = document.createElement('button');
    createButton.className = 'button-as-link regular-text';
    createButton.id = 'genre-create-button';
    if (isEmpty === 'empty') {
        createButton.innerHTML = "Looks like it's empty here, click to add something!"
    } else if (isEmpty === 'not empty') {
        createButton.innerText = "Not seeing what you want? (click to add your own)";
    }
    createButton.addEventListener('click', () => {
        let genreModalElement = document.querySelector('#genre-create-modal');
        let genreModal = new bootstrap.Modal(genreModalElement);
        window.genreModal = genreModal;
        let submitButton = document.querySelector('#genre-modal-submit');
        submitButton.addEventListener('click', submitButtonSendAndHide);
        genreModal.show();
    })
    return createButton;
}

let genreSubmit =()=>{
    let genreName = document.querySelector('#genre-modal-name').value;
    let genreDesc = document.querySelector('#genre-modal-desc').value;
    let bodyObj = {'name': genreName, 'description': genreDesc};
    fetch('/genres/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).then((response)=>{
        if (response.status === 201) {
            console.log('Genre Successfully Create')
        } else {
            console.log('Error In Create')
        }
    }).finally(()=>{
        document.querySelector('#genre-modal-name').value = '';
        document.querySelector('#genre-modal-desc').value = '';
    })
}

let submitButtonSendAndHide =()=>{
    genreSubmit();
    window.genreModal.hide();
}