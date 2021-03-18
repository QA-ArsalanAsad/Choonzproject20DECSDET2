export let genreCardBuilder =(genre)=>{
    let card = document.createElement('div');
    card.className = 'card bg-dark regular-text border border-light mb-3';
    let cardHeader = document.createElement('div');
    cardHeader.className = 'card-header';
    cardHeader.innerHTML = genre['name'];
    cardHeader.append((genre['id']));
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