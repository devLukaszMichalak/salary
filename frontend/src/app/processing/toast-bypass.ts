import { HttpContextToken } from '@angular/common/http';

export const TOAST_BYPASS = new HttpContextToken<boolean>(() => false);
