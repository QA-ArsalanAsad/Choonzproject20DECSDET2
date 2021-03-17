import {artistSearch} from './artist-search.js';
import {artistCardBuilder} from './artist-card-builder.js';
import {artistCreate} from './artist-create.js';

export let artistPageBuild = async ()=>{
    let name = document.querySelector('#input-bar').value;
    let artistList;
    await artistSearch(name)
        .then((response)=>{
            artistList = response;
        })

    let body = document.querySelector('#centre-col');
    body.innerHTML = '';

    let row = document.createElement('div');
    row.className = 'row';

    let col1 = document.createElement('div');
    col1.className = 'col-4';
    let col2 = document.createElement('div');
    col2.className = 'col-4';
    let col3 = document.createElement('div');
    col3.className = 'col-4';

    let counter = 1;

    for (let artist in artistList) {
        let artistCard = artistCardBuilder(artistList[artist]);
        switch (counter) {
            case 1:
                col1.append(artistCard);
                counter += 1;
                break;
            case 2:
                col2.append(artistCard);
                counter += 1;
                break;
            case 3:
                col3.append(artistCard)
                counter = 1;
                break;
        }
    }

    row.append(col1, col2, col3);
    body.append(row);

    let empty;
    if (artistList.length === 0) {
        empty = 'empty';
    } else {
        empty = 'not empty';
    }

    let createButton = artistCreate(empty);
    body.append(createButton);
}