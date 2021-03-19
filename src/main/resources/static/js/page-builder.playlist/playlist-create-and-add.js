// import {getAllTrackNames} from '../utils.js';
//
// export let playlistCreate =(isEmpty) =>{
//     if (document.contains(document.querySelector('#playlist-create-button'))) {
//         document.querySelector('#centre-col').removeChild(document.querySelector('#playlist-create-button'));
//     }
//     let createButton = document.createElement('button');
//     createButton.className = 'button-as-link regular-text';
//     createButton.id = 'playlist-create-button';
//     if (isEmpty === 'empty') {
//         createButton.innerHTML = "Looks like it's empty here, click to add something!"
//     } else if (isEmpty === 'not empty') {
//         createButton.innerText = "Not seeing what you want? (click to add your own)";
//     }
//     createButton.addEventListener('click', ()=>{
//         let playlistModalElement = document.querySelector('#playlist-create-modal');
//         let playlistModal = new bootstrap.Modal(playlistModalElement);
//         window.playlistModal = playlistModal;
//         let trackInput = document.querySelector('#playlist-modal-track-name');
//         let trackSelect = document.querySelector('#playlist-modal-track-select');
//         trackInput.addEventListener('input', async ()=>{
//             let currentInput = trackInput.value;
//             let namedTracks = [];
//             await fetch('/tracks/read')
//                 .then((response)=>{
//                     return response.json();
//                 }).then((responseData)=>{
//                     for (let track in responseData) {
//                         if (responseData[track]['name'].toLowerCase().includes(currentInput.toLowerCase()));
//                         namedTracks.push(responseData[track]['name']);
//                     }
//                 })
//             for (let track in namedTracks) {
//                 let option = document.createElement('option');
//                 option.innerHTML = track;
//                 trackSelect.append(option);
//             }
//         })
//         trackSelect.addEventListener('change', ()=>{
//             let currentTrack = trackSelect.value;
//             trackInput.value = currentTrack;
//         })
//         let submitButton = document.querySelector('#playlist-modal-submit');
//         submitButton.addEventListener('click', submitButtonSendAndHide);
//         let addTrackButton = document.querySelector('#add-track');
//         addTrackButton.addEventListener('click', ()=>{
//             appendTrackList();
//             resetAddTrack();
//         })
//         playlistModal.show();
//     })
//     return createButton;
// }
//
// window.listoftracks = [];
//
// let appendTrackList = ()=>{
//     let trackSelected = document.querySelector('#playlist-modal-track-name').value;
//     window.listoftracks.push(trackSelected);
// }
//
// let resetAddTrack =()=>{
//     let trackName = document.querySelector('#playlist-modal-track-name').value;
//
//     let addedTracks = document.querySelector('#added-tracks');
//     addedTracks.append(trackName + ', ');
//
//     document.querySelector('#playlist-modal-track-name').value = '';
//
//     console.log(window.listoftracks)
// }
//
// let submitButtonSendAndHide =()=>{
//     window.playlistModal.hide();
// }
//
// let playlistSubmit =()=>{
//     let playlistName = document.querySelector('#playlist-modal-name').value;
//     let playlistDescription = document.querySelector('#playlist-modal-desc').value;
//
//
// }