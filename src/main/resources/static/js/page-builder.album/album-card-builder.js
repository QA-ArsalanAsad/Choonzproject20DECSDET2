export let albumCardBuilder =(album)=>{
    let card = document.createElement('div');
    card.className = 'card bg-dark regular-text border border-light mb-3';
    let cardHeader = document.createElement('div');
    cardHeader.className = 'card-header';
    cardHeader.innerHTML = album['name'];
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