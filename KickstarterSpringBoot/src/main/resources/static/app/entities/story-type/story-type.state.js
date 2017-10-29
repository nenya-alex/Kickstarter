(function() {
    'use strict';

    angular
        .module('kickApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('story-type', {
            parent: 'entity',
            url: '/story-type',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'StoryTypes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/story-type/story-types.html',
                    controller: 'StoryTypeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('story-type-detail', {
            parent: 'story-type',
            url: '/story-type/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'StoryType'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/story-type/story-type-detail.html',
                    controller: 'StoryTypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'StoryType', function($stateParams, StoryType) {
                    return StoryType.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'story-type',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('story-type-detail.edit', {
            parent: 'story-type-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/story-type/story-type-dialog.html',
                    controller: 'StoryTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StoryType', function(StoryType) {
                            return StoryType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('story-type.new', {
            parent: 'story-type',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/story-type/story-type-dialog.html',
                    controller: 'StoryTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('story-type', null, { reload: 'story-type' });
                }, function() {
                    $state.go('story-type');
                });
            }]
        })
        .state('story-type.edit', {
            parent: 'story-type',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/story-type/story-type-dialog.html',
                    controller: 'StoryTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StoryType', function(StoryType) {
                            return StoryType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('story-type', null, { reload: 'story-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('story-type.delete', {
            parent: 'story-type',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/story-type/story-type-delete-dialog.html',
                    controller: 'StoryTypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['StoryType', function(StoryType) {
                            return StoryType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('story-type', null, { reload: 'story-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
