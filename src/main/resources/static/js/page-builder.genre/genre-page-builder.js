import {genreSearch} from './genre-search.js';
import {genreCardBuilder} from './genre-card-builder.js';
import {genreCreate} from './genre-create.js';

export let genrePageBuild = async ()=>{
    let name = document.querySelector('#input-bar').value;
    let genreList;
    await genreSearch(name)
        .then((response)=>{
            genreList = response;
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

    for (let genre in genreList) {
        let genreCard = genreCardBuilder(genreList[genre])
        switch (counter) {
            case 1:
                col1.append(genreCard);
                counter += 1;
                break;
            case 2:
                col2.append(genreCard);
                counter += 1;
                break;
            case 3:
                col3.append(genreCard);
                counter = 1;
                break;
        }
    }

    row.append(col1, col2, col3);
    body.append(row);

    let empty;
    if (genreList.length === 0) {
        empty = 'empty';
    } else {
        empty = 'not empty';
    }

    let createButton = genreCreate(empty);
    body.append(createButton);
}