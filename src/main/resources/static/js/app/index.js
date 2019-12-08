const index = {
    init: () => {
        $('#btn-save').click(() => {
            index.save();
        });
        $('#btn-update').click(() => {
            index.update();
        });
        $('#btn-delete').click(() => {
            index.delete();
        })

    },
    save: () => {
        const data = {
            title: $("#title").val(),
            author: $("#author").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: 'post',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (success) {
            alert(success.body);

        }).fail(function (error) {
            console.log(error.responseJSON);
            alert(error.responseJSON.message);
        });
    },
    update: () => {
        const data = {
            title: $("#title").val(),
            content: $("#content").val()
        }

        const id = $("#id").val();

        $.ajax({
            type: 'put',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done((success) => {
            alert(success.body);

        }).fail((error) => {
            console.log(error.responseJSON);
            alert(error.responseJSON.message);
        });

    },
    delete: () => {
        const id = $("#id").val();

        $.ajax({
            type: 'delete',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done((success) => {
            alert(success.body);
            console.log(success)
        }).fail((error) => {
            console.log(error.responseJSON);
            alert(error.responseJSON.message);
        });

    }
}
index.init();
