export let albumSearch = async (name) => {
    let allAlbumsList = [];
    await fetch('/albums/read')
        .then((response) => {
            return response.json();
        }).then((responseData) => {
            allAlbumsList = responseData;
        })
    let namedAlbumList = [];
    if (name !== '' || name !== 'all') {
        for (let album in allAlbumsList) {
            if (allAlbumsList[album]['name'].toLowerCase().includes(name.toLowerCase())) {
                namedAlbumList.push(allAlbumsList[album]);
            }
        }
    } else {
        namedAlbumList = allAlbumsList;
    }
    return namedAlbumList;
}