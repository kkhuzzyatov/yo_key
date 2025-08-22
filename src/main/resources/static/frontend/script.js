document.addEventListener('DOMContentLoaded', async function() {
    const leftMenuButton = document.getElementById('leftMenuButton');
    const catalogButton = document.getElementById('catalogButton');
    const closeButton = document.getElementById('closeButton');
    const openCatalogButton = document.getElementById('openCatalogButton');
    const closeLeftMenuScreen = document.querySelector('.close-left-menu-screen');
    const openLeftMenuScreen = document.querySelector('.open-left-menu-screen');
    function openMenu() {
        closeLeftMenuScreen.style.display = 'none';
        openLeftMenuScreen.style.display = 'flex';
    }
    function closeMenu() {
        openLeftMenuScreen.style.display = 'none';
        closeLeftMenuScreen.style.display = 'flex';
    }
    leftMenuButton.addEventListener('click', openMenu);
    catalogButton.addEventListener('click', openMenu);
    closeButton.addEventListener('click', closeMenu);
    openCatalogButton.addEventListener('click', closeMenu);
    try {
        const [bgData, descData, percentData, headerData, categoriesData] = await Promise.all([
            fetch('http://localhost:8080/api/ui_elements/get?name=mainBg').then(res => res.text()),
            fetch('http://localhost:8080/api/ui_elements/get?name=discountDescription').then(res => res.text()),
            fetch('http://localhost:8080/api/ui_elements/get?name=discountPercent').then(res => res.text()),
            fetch('http://localhost:8080/api/ui_elements/get?name=headerOfLeftMenu').then(res => res.text()),
            fetch('http://localhost:8080/api/segments/get_all').then(res => res.text())
        ]);

        if (!bgData || !descData || !percentData || !headerData || !categoriesData) {
            throw new Error('Один или несколько запросов вернули пустой ответ');
        }
        
        document.getElementById('main-bg').src = bgData;
        document.getElementById('open-left-menu-main-bg').src = bgData;
        
        const discountDescriptions = document.querySelectorAll('.discount-description');
        discountDescriptions.forEach(description => {
            description.innerHTML = descData;
        });
        
        const discountPercents = document.querySelectorAll('.discount-percent');
        discountPercents.forEach(percent => {
            percent.innerHTML = percentData;
        });
        document.getElementById('leftMenuHeader').innerHTML = headerData

        menuCategories = document.getElementById('menuCategories')
        const words = categoriesData.split(' ');

        words.forEach(word => {
            let category = document.createElement('category');
            category.innerHTML = '<li>' + word + '</li>'
            document.body.append(category);
            menuCategories.appendChild(category);
        });
    } catch (error) {
        console.error('Ошибка при загрузке данных:', error);
    }
});