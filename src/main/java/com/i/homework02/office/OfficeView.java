package com.i.homework02.office;

import com.fasterxml.jackson.annotation.JsonView;

public class OfficeView {
        public interface OfficeFindById{}
        public interface OfficeFindByOrgidOffnameOffPhoneOffisactive{}
}
//@JsonView({OfficeView.OfficeFindByOrgidOffnameOffPhoneOffisactive.class})

