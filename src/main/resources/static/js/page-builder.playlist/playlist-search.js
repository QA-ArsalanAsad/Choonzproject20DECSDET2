export let playlistSearch = async (name) => {
    let allPlaylistList = [];
    await fetch('/playlists/read')
        .then((response) => {
            return response.json();
        }).then((responseData) => {
            allPlaylistList = responseData;
        })

    let namedPlaylistList = [];
    if (name !== '' || name !== 'all') {
        for (let playlist in allPlaylistList) {
            if (allPlaylistList[playlist]['name'].toLowerCase().includes(name.toLowerCase())) {
                namedPlaylistList.push(allPlaylistList[playlist]);
            }
        }
    } else {
        namedPlaylistList = allPlaylistList;
    }
    return namedPlaylistList;
}