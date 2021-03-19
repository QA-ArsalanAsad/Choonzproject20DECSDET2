export let genreCardBuilder =(genre)=>{
    let card = document.createElement('div');
    card.className = 'card bg-dark regular-text border border-light mb-3';
    let cardHeader = document.createElement('div');
    cardHeader.className = 'card-header';
    cardHeader.innerHTML = genre['name'];
    cardHeader.append(deleteGenre(genre['id']));
    cardHeader.append(updateGenre(genre));
    let cardBody = document.createElement('div');
    cardBody.className = 'card-body';

    let desc = document.createElement('div');
    desc.innerHTML = `Description: \n ${genre['description']}`

    let allAlbums = [];
    for (let album in genre['albums']) {
        allAlbums.push(genre['albums'][album]['name'])
    }

    let albums = document.createElement('div');
    albums.innerHTML = `Albums: \n ${allAlbums}`;

    cardBody.append(desc, albums);
    card.append(cardHeader, cardBody);
    return card;
}

let deleteGenre =(id)=>{
    let deleteButton = document.createElement('button');
    deleteButton.className = 'cancel';
    deleteButton.innerHTML = `&#10060`;
    deleteButton.id = `delete-genre-${id}`;
    deleteButton.addEventListener('click', async ()=>{
        fetch(`/genres/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
    })
    return deleteButton;
}

let updateGenre =(genre)=>{
    let updateButton = document.createElement('button');
    updateButton.className = 'update-button';
    updateButton.innerHTML = `&#9998`;
    updateButton.id = `update-artist-${genre['id']}`;
    window.currentGenre = genre;
    updateButton.addEventListener('click', () => {
        let genreModalTitle = document.querySelector('#artist-modal-title');
        genreModalTitle.innerHTML = 'Update A Genre';
        let genreModalName = document.querySelector('#genre-modal-name')
        genreModalName.value = window.currentGenre['name'];
        let genreModalDesc = document.querySelector('#genre-modal-desc');
        genreModalDesc.value = window.currentGenre['description'];
        let genreModalElement = document.querySelector('#genre-create-modal');
        let genreModal = new bootstrap.Modal(genreModalElement);
        window.genreModal = genreModal;
        let submitButton = document.querySelector('#genre-modal-submit');
        submitButton.addEventListener('click', submitButtonSendAndHide
        );
        genreModal.show();
    })
    return updateButton;
}

let genreUpdate = () => {
    let id = window.currentGenre['id'];
    let genreName = document.querySelector('#genre-modal-name').value;
    let genreDesc = document.querySelector('#genre-modal-desc').value;
    let bodyObj = {'id': id, 'name': genreName, 'description': genreDesc};
    fetch(`/genres/update/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).finally(() => {
        document.querySelector('#genre-modal-name').value = '';
        document.querySelector('#genre-modal-desc').value = '';
        let genreModalTitle = document.querySelector('#genre-modal-title');
        fetch(`/genres/delete/${id}`, {
            method: 'DELETE'
        });
        genreModalTitle.innerHTML = 'Update A Genre';
    })
}

let submitButtonSendAndHide = () => {
    genreUpdate();
    window.artistModal.hide();
}