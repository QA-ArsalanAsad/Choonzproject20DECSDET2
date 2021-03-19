export let albumCardBuilder =(album)=>{
    let card = document.createElement('div');
    card.className = 'card bg-dark regular-text border border-light mb-3';
    let cardHeader = document.createElement('div');
    cardHeader.className = 'card-header';
    cardHeader.innerHTML = album['name'];
    cardHeader.append(deleteAlbum(album['id']));
    let cardBody = document.createElement('div');
    cardBody.className = 'card-body';

    let allTracks = [];
    for (let track in album['tracks']) {
        allTracks.push(album['tracks'][track]['name']);
    }
    cardBody.innerHTML = `Tracks: \n ${allTracks}`;

    card.append(cardHeader, cardBody);
    return card;
}

let deleteAlbum =(id)=>{
    let deleteButton = document.createElement('button');
    deleteButton.className = 'cancel';
    deleteButton.innerHTML = `&#10060`;
    deleteButton.id = `delete-album-${id}`;
    deleteButton.addEventListener('click', async ()=>{
        fetch(`/albums/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
    })
    return deleteButton;
}

let updateAlbum = (album) => {
    let updateButton = document.createElement('button');
    updateButton.className = 'update-button';
    updateButton.innerHTML = `&#9998`;
    updateButton.id = `update-artist-${album['id']}`;
    window.currentAlbum = album;
    updateButton.addEventListener('click', () => {
        let artistModalTitle = document.querySelector('#artist-modal-title');
        artistModalTitle.innerHTML = 'Update An Artist';
        let artistModalName = document.querySelector('#artist-modal-name')
        artistModalName.value = window.currentArtist['name'];
        let artistModalElement = document.querySelector('#artist-create-modal');
        let artistModal = new bootstrap.Modal(artistModalElement);
        window.artistModal = artistModal;
        let submitButton = document.querySelector('#artist-modal-submit');
        submitButton.addEventListener('click', submitButtonSendAndHide
        );
        artistModal.show();
    })
    return updateButton;
}

let artistUpdate = () => {
    let id = window.currentArtist['id'];
    let artistName = document.querySelector('#artist-modal-name').value;
    let bodyObj = {'id': id, 'name': artistName};
    fetch(`/artists/update/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).finally(() => {
        document.querySelector('#artist-modal-name').value = '';
        let artistModalTitle = document.querySelector('#artist-modal-title');
        fetch(`/artists/delete/${id}`, {
            method: 'DELETE'
        });
        artistModalTitle.innerHTML = 'Create An Artist';
    })
}

let submitButtonSendAndHide = () => {
    artistUpdate();
    window.artistModal.hide();
}