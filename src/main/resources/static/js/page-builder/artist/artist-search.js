export let artistSearch = async (name) => {
    let allArtistList = [];
    await fetch('/artists/read')
        .then((response) => {
            return response.json();
        }).then((responseData) => {
            allArtistList = responseData;
        })
    let namedArtistList = [];
    if (name !== '' || name !== 'all') {
        for (let artist in allArtistList) {
            if (allArtistList[artist]['name'].toLowerCase().includes(name.toLowerCase())) {
                namedArtistList.push(allArtistList[artist]);
            }
        }
    } else {
        namedArtistList = allArtistList;
    }
    return namedArtistList;
}