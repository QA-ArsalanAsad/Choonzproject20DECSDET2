export let genreSearch = async (name) => {
    let allGenreList = [];
    await fetch('/genres/read')
        .then((response) => {
            return response.json();
        }).then((responseData) => {
            allGenreList = responseData;
        })
    let namedGenreList = [];
    if (name !== '' || name !== 'all') {
        for (let genre in allGenreList) {
            if (allGenreList[genre]['name'].toLowerCase().includes(name.toLowerCase())) {
                namedGenreList.push(allGenreList[genre]);
            }
        }
    } else {
        namedGenreList = allGenreList;
    }
    return namedGenreList;
}