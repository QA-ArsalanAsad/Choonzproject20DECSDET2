export let artistCardBuilder =(artist)=>{
    let card = document.createElement('div');
    card.className = 'card bg-dark regular-text border border-light mb-3';
    let cardHeader = document.createElement('div');
    cardHeader.className = 'card-header';
    cardHeader.innerHTML = artist['name'];
    cardHeader.append(deleteArtist(artist['id']));
    let cardBody = document.createElement('div');
    cardBody.className = 'card-body';

    let allAlbums = [];
    for (let album in artist['albums']) {
        allAlbums.push(artist['albums'][album]['name'])
    }
    cardBody.innerHTML = `Albums: \n ${allAlbums}`

    card.append(cardHeader, cardBody);
    return card;
}

let deleteArtist =(id)=>{
    let deleteButton = document.createElement('button');
    deleteButton.className = 'cancel';
    deleteButton.innerHTML = `&#10060`;
    deleteButton.id = `delete-artist-${id}`;
    deleteButton.addEventListener('click', async ()=>{
        fetch(`/artists/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
    })
    return deleteButton;
}