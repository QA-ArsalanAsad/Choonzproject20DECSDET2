export let playlistCardBuilder =(playlist)=>{
    let card = document.createElement('div');
    card.className = 'card bg-dark regular-text border border-light mb-3';
    let cardHeader = document.createElement('div');
    cardHeader.className = 'card-header';
    cardHeader.innerHTML = playlist['name'];
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