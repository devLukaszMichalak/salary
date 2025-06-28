import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PageService } from '../page.service';
import { PaginationComponent } from './pagination.component';

describe('PaginationComponent', () => {
  let component: PaginationComponent;
  let fixture: ComponentFixture<PaginationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PaginationComponent],
      providers: [PageService]
    }).compileComponents();

    fixture = TestBed.createComponent(PaginationComponent);
    fixture.componentRef.setInput('page', {
      size: 10,
      number: 0,
      totalElements: 100,
      totalPages: 10
    });
    component = fixture.componentInstance;

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
