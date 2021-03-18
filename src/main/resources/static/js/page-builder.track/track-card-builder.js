export let trackCardBuilder =(track)=> {
    let card = document.createElement('div');
    card.className = 'card bg-dark regular-text border border-light mb-3';
    let cardHeader = document.createElement('div');
    cardHeader.className = 'card-header';
    cardHeader.innerHTML = track['name'];
    cardHeader.append((track['id']));
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

let deleteTrack =(id)=>{
    let deleteButton = document.createElement('button');
    deleteButton.className = 'cancel';
    deleteButton.innerHTML = `&#10060`;
    deleteButton.id = `delete-track-${id}`;
    deleteButton.addEventListener('click', async ()=>{
        fetch(`/tracks/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
    })
    return deleteButton;
}