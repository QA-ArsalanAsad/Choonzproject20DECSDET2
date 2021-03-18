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