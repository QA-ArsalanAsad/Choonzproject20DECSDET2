export let trackSearch = async (name) => {
    let allTracksList = [];
    await fetch('/tracks/read')
        .then((response)=>{
            return response.json();
        }).then((responseData)=>{
            allTracksList = responseData;
        })
    let namedTrackList = [];
    if (name !== '' || name !== 'all') {
        for (let track in allTracksList) {
            if (allTracksList[track]['name'].toLowerCase().includes(name.toLowerCase())) {
                namedTrackList.push(allTracksList[track]);
            }
        }
    } else {
        namedTrackList = allTracksList;
    }
    return namedTrackList;
}