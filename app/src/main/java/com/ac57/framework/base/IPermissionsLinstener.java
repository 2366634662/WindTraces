package com.ac57.framework.base;

import java.util.List;

/**
 */

public interface IPermissionsLinstener {
    void permissionSuccess();

    void permissionDenied(List<String> deniedPermissions);

}
