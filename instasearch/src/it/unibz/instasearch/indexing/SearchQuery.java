/*
 * Copyright (c) 2009 Andrejs Jermakovics. All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Andrejs Jermakovics - initial implementation
 */
package it.unibz.instasearch.indexing;

import java.util.Map;
import java.util.Set;

/**
 * A search query
 */
public class SearchQuery {

  private String searchString;
  /**
   * maximum number of results to return ({@link #UNLIMITED_RESULTS} for all results)
   */
  private int maxResults = UNLIMITED_RESULTS;
  private boolean exact = true;
  private boolean fuzzy = false;
  private boolean canceled = false;
  private String currentProject;
  private Map<Field, Set<String>> filter;

  /**
   * Unlimited search results
   */
  public static final int UNLIMITED_RESULTS = -1;

  public SearchQuery(final String searchString, final int maxResults) {
    this.searchString = searchString;
    this.maxResults = maxResults;
  }

  /**
   * @param searchQuery
   */
  public SearchQuery(final SearchQuery searchQuery) {
    this.searchString = searchQuery.searchString;
    this.maxResults = searchQuery.maxResults;
    this.fuzzy = searchQuery.fuzzy;
    this.exact = searchQuery.exact;
  }

  /**
   * @return the searchString
   */
  public String getSearchString() {
    return this.searchString;
  }

  /**
   * @return maxResults
   */
  public int getMaxResults() {
    return this.maxResults;
  }

  public boolean isLimited() {
    return (this.maxResults != UNLIMITED_RESULTS);
  }

  /**
   * @param maxResults
   */
  public void setMaxResults(final int maxResults) {
    this.maxResults = maxResults;
  }

  /**
   * Whether the query is exact or should be split into tokens
   * 
   * @return the exact
   */
  public boolean isExact() {
    return this.exact;
  }

  /**
   * @param exact Whether the query is exact or should be split into tokens
   */
  public void setExact(final boolean exact) {
    this.exact = exact;
  }

  /**
   * @return fuzzy
   */
  public boolean isFuzzy() {
    return this.fuzzy;
  }

  /**
   * Make a fuzzy query to find similar matches
   * 
   * @param fuzzy
   */
  public void setFuzzy(final boolean fuzzy) {
    this.fuzzy = fuzzy;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }
    if ((obj == null) || !(obj instanceof SearchQuery)) {
      return false;
    }

    SearchQuery sq = (SearchQuery) obj;

    if (this.fuzzy != sq.isFuzzy()) {
      return false;
    }
    if (this.exact != sq.isExact()) {
      return false;
    }
    if (this.maxResults != sq.getMaxResults()) {
      return false;
    }
    if (!this.searchString.equals(sq.getSearchString())) {
      return false;
    }
    if ((this.filter != null) && (sq.filter != null)) {
      if (!this.filter.equals(sq.filter)) {
        return false;
      }
    }
    else if (this.filter != sq.filter) {
      return false; // one is null
    }

    return true;
  }

  /**
   * Cancel the query during its execution
   * 
   * @param canceled
   */
  public void setCanceled(final boolean canceled) {
    this.canceled = canceled;
  }

  /**
   * @param searchString the searchString to set
   */
  public void setSearchString(final String searchString) {
    this.searchString = searchString;
  }

  /**
   * @return true if search query has been canceled
   */
  public boolean isCanceled() {
    return this.canceled;
  }

  /**
   * The project that was active when the query was run
   * 
   * @return the current Project
   */
  public String getCurrentProject() {
    return this.currentProject;
  }

  /**
   * @param currentProject
   */
  public void setCurrentProject(final String currentProject) {
    this.currentProject = currentProject;
  }

  /**
   * @param filter
   */
  public void setFilter(final Map<Field, Set<String>> filter) {
    this.filter = filter;
  }

  /**
   * @return the filter
   */
  public Map<Field, Set<String>> getFilter() {
    return this.filter;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
