export let playlistCardBuilder =(playlist)=>{
    let card = document.createElement('div');
    card.className = 'card bg-dark regular-text border border-light mb-3';
    let cardHeader = document.createElement('div');
    cardHeader.className = 'card-header';
    cardHeader.innerHTML = playlist['name'];
    cardHeader.append(deletePlaylist(playlist['id']));
    let cardBody = document.createElement('div');
    cardBody.className = 'card-body';

    let desc = document.createElement('div');
    desc.innerHTML = `Description: \n ${playlist['description']}`

    let allTracks = [];
    for (let track in playlist['tracks']) {
        allTracks.push(playlist['tracks'][track]['track']['name'])
    }

    let tracks = document.createElement('div');
    tracks.innerHTML = `Tracks: \n ${allTracks}`;

    cardBody.append(desc, tracks);
    card.append(cardHeader, cardBody);

    return card;
}

let deletePlaylist =(id)=>{
    let deleteButton = document.createElement('button');
    deleteButton.className = 'cancel';
    deleteButton.innerHTML = `&#10060`;
    deleteButton.id = `delete-playlists-${id}`;
    deleteButton.addEventListener('click', async ()=>{
        fetch(`/playlists/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
    })
    return deleteButton;
}