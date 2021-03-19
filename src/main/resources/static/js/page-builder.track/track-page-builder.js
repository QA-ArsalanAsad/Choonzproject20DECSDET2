import {trackSearch} from './track-search.js';
import {trackCardBuilder} from './track-card-builder.js';
import {trackCreate} from './track-create.js';

export let trackPageBuild = async () => {
    let name = document.querySelector('#input-bar').value;
    let trackList;
    await trackSearch(name)
        .then((response)=>{
            trackList = response;
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

    for (let track in trackList) {
        let trackCard = trackCardBuilder(trackList[track]);
        switch (counter) {
            case 1:
                col1.append(trackCard);
                counter += 1;
                break;
            case 2:
                col2.append(trackCard);
                counter += 1;
                break;
            case 3:
                col3.append(trackCard);
                counter += 1;
                break;
        }
    }

    row.append(col1, col2, col3);
    body.append(row);

    let empty;
    if (trackList.length === 0) {
        empty = 'empty';
    } else {
        empty = 'not empty';
    }

    let createButton = trackCreate(empty);
    body.append(createButton);
}