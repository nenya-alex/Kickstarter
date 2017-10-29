(function() {
    'use strict';
    angular
        .module('kickApp')
        .factory('StoryType', StoryType);

    StoryType.$inject = ['$resource'];

    function StoryType ($resource) {
        var resourceUrl =  'api/story-types/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
