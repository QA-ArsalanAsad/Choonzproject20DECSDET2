import {albumSearch} from './album-search.js';
import {albumCardBuilder} from './album-card-builder.js';
import {albumCreate} from './album-create.js';

export let albumPageBuild = async () =>{
    let name = document.querySelector('#input-bar').value;
    let albumList;
    await albumSearch(name)
        .then((response)=>{
            albumList = response;
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

    for (let album in albumList) {
        let albumCard = albumCardBuilder(albumList[album]);
        switch (counter) {
            case 1:
                col1.append(albumCard);
                counter += 1;
                break;
            case 2:
                col2.append(albumCard);
                counter += 1
                break;
            case 3:
                col3.append(albumCard);
                counter = 1;
                break;
        }
    }

    row.append(col1, col2, col3);
    body.append(row);

    let empty;
    if (albumList.length === 0) {
        empty = 'empty';
    } else {
        empty = 'not empty';
    }

    let createButton = albumCreate(empty);
    body.append(createButton);
}