export let trackCardBuilder =(track)=> {
    let card = document.createElement('div');
    card.className = 'card bg-dark regular-text border border-light mb-3';
    let cardHeader = document.createElement('div');
    cardHeader.className = 'card-header';
    cardHeader.innerHTML = track['name'];
    let cardBody = document.createElement('div');
    cardBody.className = 'card-body';

    for (let prop in track) {
        if (prop === 'id') {
            continue;
        } else {
            cardBody.innerHTML += `${prop}: ${track[prop]} <br>`;
        }
    }

    card.append(cardHeader, cardBody);
    return card;
}