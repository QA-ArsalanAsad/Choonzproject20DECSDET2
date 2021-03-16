export let additionLink = (type) => {
    let button = document.createElement('button');
    button.className = "button-as-link";
    button.innerText = "Not seeing what you want? (Add Your Own)";
    button.addEventListener("click", () => {
        buttonAction(type)
    });
    return button;
}

let counter = 0;

let buttonAction = (type) => {
    counter += 1;
    if (type === "Artist") {
        createArtist();
    } else if (type === "Genre") {
        createGenre();
    } else if (type === "Playlist") {
        createPlaylist();
    } else if (type === "Album") {
        createAlbum();
    } else if (type === "Track") {
        createTrack();
    }
}

let createArtist = () => {

    let table = document.querySelector('#results-table');

    let tr = document.createElement('tr');
    let refNum = counter;
    tr.id = `input-row-${refNum}`;

    let col1 = document.createElement('td');
    col1.className = "regular-text"
    col1.innerHTML = "NEW";

    let col2 = document.createElement('td');

    let name = document.createElement('input');
    name.className = "form-control";
    name.id = "artist-name";

    col2.append(name);

    let col3 = document.createElement('td');
    col3.className = "regular-text";
    col3.innerHTML = "N/A"

    let col4 = document.createElement('td');

    let confirm = document.createElement('button');
    confirm.className = "confirm";
    confirm.innerHTML = `&#10004`;
    confirm.addEventListener("click", confirmCreateArtist);

    let cancel = document.createElement('button');
    cancel.className = "cancel";
    cancel.innerHTML = `&#10006`;
    cancel.addEventListener("click", () => {
        cancelCreate(refNum)
    });

    col4.append(confirm, cancel);

    tr.append(col1, col2, col3, col4);
    table.append(tr);
}

let createGenre = () => {

    let table = document.querySelector('#results-table');

    let tr = document.createElement('tr');
    let refNum = counter;
    tr.id = `input-row-${refNum}`;

    let col1 = document.createElement('td');
    col1.className = "regular-text"
    col1.innerHTML = "NEW";

    let col2 = document.createElement('td');

    let name = document.createElement('input');
    name.className = "form-control";
    name.id = "genre-name";

    col2.append(name);

    let col3 = document.createElement('td');

    let desc = document.createElement('input');
    desc.className = "form-control";
    desc.id = "genre-desc";

    col3.append(desc);

    let col4 = document.createElement('td');
    col4.className = "regular-text";
    col4.innerHTML = "N/A";

    let col5 = document.createElement('td');

    let confirm = document.createElement('button');
    confirm.className = "confirm";
    confirm.innerHTML = `&#10004`;
    confirm.addEventListener("click", confirmCreateGenre);

    let cancel = document.createElement('button');
    cancel.className = "cancel";
    cancel.innerHTML = `&#10006`;
    cancel.addEventListener("click", () => {
        cancelCreate(refNum)
    });

    col5.append(confirm, cancel);

    tr.append(col1, col2, col3, col4, col5);
    table.append(tr);
}

let createPlaylist = () => {

    let table = document.querySelector('#results-table');

    let tr = document.createElement('tr');
    let refNum = counter;
    tr.id = `input-row-${refNum}`;

    let col1 = document.createElement('td');
    col1.className = "regular-text"
    col1.innerHTML = "NEW";

    let col2 = document.createElement('td');

    let name = document.createElement('input');
    name.className = "form-control";
    name.id = "playlist-name";

    col2.append(name);

    let col3 = document.createElement('td');

    let desc = document.createElement('input');
    desc.className = "form-control";
    desc.id = "playlist-desc";

    col3.append(desc);

    let col4 = document.createElement('td');
    col4.className = "regular-text";
    col4.innerHTML = "N/A";

    let col5 = document.createElement('td');

    let confirm = document.createElement('button');
    confirm.className = "confirm";
    confirm.innerHTML = `&#10004`;
    confirm.addEventListener("click", confirmCreatePlaylist);

    let cancel = document.createElement('button');
    cancel.className = "cancel";
    cancel.innerHTML = `&#10006`;
    cancel.addEventListener("click", () => {
        cancelCreate(refNum)
    });

    col5.append(confirm, cancel);

    tr.append(col1, col2, col3, col4, col5);
    table.append(tr);
}

let createAlbum = () => {

    let table = document.querySelector('#results-table');

    let tr = document.createElement('tr');
    let refNum = counter;
    tr.id = `input-row-${refNum}`;

    let col1 = document.createElement('td');
    col1.className = "regular-text"
    col1.innerHTML = "NEW";

    let col2 = document.createElement('td');

    let name = document.createElement('input');
    name.className = "form-control";
    name.id = "album-name";

    col2.append(name);

    let col3 = document.createElement('td');
    col3.className = "regular-text";
    col3.innerHTML = "N/A";

    let col4 = document.createElement('td');

    let artistName = document.createElement('input');
    artistName.className = "form-control";
    artistName.id = "artist-name";

    col4.append(artistName);

    let col5 = document.createElement('td');

    let genre = document.createElement('input');
    genre.className = "form-control";
    genre.id = "genre-name";

    col5.append(genre);

    let col6 = document.createElement('td');

    let confirm = document.createElement('button');
    confirm.className = "confirm";
    confirm.innerHTML = `&#10004`;
    confirm.addEventListener("click", confirmCreateAlbum);

    let cancel = document.createElement('button');
    cancel.className = "cancel";
    cancel.innerHTML = `&#10006`;
    cancel.addEventListener("click", () => {
        cancelCreate(refNum)
    });

    col6.append(confirm, cancel);

    tr.append(col1, col2, col3, col4, col5, col6);
    table.append(tr);
}

let createTrack = () => {

    let table = document.querySelector('#results-table');

    let tr = document.createElement('tr');
    let refNum = counter;
    tr.id = `input-row-${refNum}`;

    let col1 = document.createElement('td');
    col1.className = "regular-text"
    col1.innerHTML = "NEW";

    let col2 = document.createElement('td');

    let name = document.createElement('input');
    name.className = "form-control";
    name.id = "track-name";

    col2.append(name);

    let col3 = document.createElement('td');

    let albumName = document.createElement('input');
    albumName.className = "form-control";
    albumName.id = "album-name";

    col3.append(albumName);

    let col4 = document.createElement('td');

    let duration = document.createElement('input');
    duration.className = "form-control";
    duration.id = "duration";

    col4.append(duration);

    let col5 = document.createElement('td');
    col5.className = "regular-text";
    col5.innerHTML = "N/A";

    let col6 = document.createElement('td');

    let confirm = document.createElement('button');
    confirm.className = "confirm";
    confirm.innerHTML = `&#10004`;
    confirm.addEventListener("click", confirmCreateTrack);

    let cancel = document.createElement('button');
    cancel.className = "cancel";
    cancel.innerHTML = `&#10006`;
    cancel.addEventListener("click", () => {
        cancelCreate(refNum)
    });

    col6.append(confirm, cancel);

    tr.append(col1, col2, col3, col4, col5, col6);
    table.append(tr);
}

let cancelCreate = (id) => {
    let resultsTable = document.querySelector('#results-table');
    resultsTable.removeChild(document.querySelector(`#input-row-${id}`));
}

let confirmCreateArtist = () => {
    let artistName = document.querySelector('#artist-name').value;
    let bodyObj = {'name': artistName};
    fetch('artists/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).then(() => {
        cancelCreate()
    })
        .finally(() => {
            let searchButton = document.querySelector('#search-button');
            searchButton.click();
        })
}

let confirmCreateGenre = () => {
    let genreName = document.querySelector('#genre-name').value;
    let genreDesc = document.querySelector('#genre-desc').value;
    let bodyObj = {'name': genreName, 'desc': genreDesc};
    fetch('genres/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).then(() => {
        cancelCreate()
    })
        .finally(() => {
            let searchButton = document.querySelector('#search-button');
            searchButton.click();
        })
}

let confirmCreatePlaylist = () => {
    let playlistName = document.querySelector('#playlist-name').value;
    let playlistDesc = document.querySelector('#playlist-desc').value;
    let artwork = 'NOT IMPLEMENTED YET';
    let bodyObj = {'name': playlistName, 'desc': playlistDesc, 'artwork': artwork};
    fetch('playlists/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).then(() => {
        cancelCreate()
    })
        .finally(() => {
            let searchButton = document.querySelector('#search-button');
            searchButton.click();
        })
}

let confirmCreateAlbum = async () => {

    let albumName = document.querySelector('#album-name').value;
    let artistName = document.querySelector('#artist-name').value;
    let genreName = document.querySelector('#genre-name').value;
    let coverImg = 'NOT IMPLEMENTED YET';

    let artistID;
    let genreID;

    await fetch('artists/read')
        .then((response) => {
            return response.json();
        }).then((responseData) => {
            for (let artistLocal in responseData) {
                if (responseData[artistLocal]['name'] === artistName) {
                    artistID = responseData[artistLocal]['id'];
                    break;
                }
            }
        })

    await fetch('genres/read')
        .then((response) => {
            return response.json();
        }).then((responseData) => {
            for (let genreLocal in responseData) {
                if (responseData[genreLocal]['name'] === genreName) {
                    genreID = responseData[genreLocal]['id'];
                    break;
                }
            }
        })

    let bodyObj = {
        'name': albumName,
        'artist': {'id': artistID},
        'genre': {'id': genreID},
        'coverImg': coverImg
    }

    fetch('albums/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).then(() => {
        cancelCreate()
    })
        .finally(() => {
            let searchButton = document.querySelector('#search-button');
            searchButton.click();
        })
}

let confirmCreateTrack = async () => {

    let trackName = document.querySelector('#track-name').value;
    let albumName = document.querySelector('#album-name').value;
    let duration = document.querySelector('#duration').value;

    let albumID;

    await fetch('albums/read')
        .then((response) => {
            return response.json();
        }).then((responseData) => {
            for (let albumLocal in responseData) {
                if (responseData[albumLocal]['name'] === albumName) {
                    albumID = responseData[albumLocal]['id'];
                }
            }
        })

    let bodyObj = {
        'name': trackName,
        'album': {
            'id': albumID
        },
        'playlist': {
            'id': 1
        },
        'duration': duration,
        'lyrics': 'File to be uploaded!'
    }

    fetch('tracks/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    }).then(() => {
        cancelCreate()
    })
        .finally(() => {
            let searchButton = document.querySelector('#search-button');
            searchButton.click();
        })
}
